package com.example.x5;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface Postable {
    @POST("method/wall.post")
    Call<PostReponse> make_post (@Body PostBody postBody);
}

public class ApiHelper {
    String token = "vk1.a.e8-3qzf05-kWGIaoI_CXxtQL62v7GpyuUvL1vydLH8JORd7TErR3N6wMdXg50_yXqu2UGlTI3EixkeldPXLgtwiNl_s4HputvmpfTYzMcSStnLgT71frbBNVuN8Vo11eipmOQf9wm3rK22_-EzJg0VqbnM_CYAPMMoyT3qxMtij32Q-wZNJ4fDbEQ2YBY60YjpLyRV3iPxUt84xZY-WuAA";
    int vk_id;
    String url = "https://api.vk.com/method/wall.post";
    String message;

    public ApiHelper(String vk_id) {
        this.vk_id = Integer.parseInt(vk_id);
    }

    public void get_message(int points) {
        message = "Я получил(-а) " + points + "очков!";
    }

    Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
    Postable postable = retrofit.create(Postable.class);

    PostBody body = new PostBody(token, vk_id, message);

}

class PostBody{
    public PostBody(String access_token, int owner_id, String message) {
        this.access_token = access_token;
        this.owner_id = owner_id;
        this.message = message;
    }

    String access_token;
    int owner_id;
    String message;
}

class PostReponse{
    int post_id;
}