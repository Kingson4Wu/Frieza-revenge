package com.kxw.http.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by kingsonwu on 16/7/23.
 * <a href='http://square.github.io/retrofit/'>@link</a>
 */
public class RetrofitTest {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.listRepos("kingson4wu");


        Response<List<Repo>> response = repos.execute();

        List<Repo> list = response.body();

        for (Repo repo: list){

            System.out.println(repo.getFull_name());
        }




    }
}
