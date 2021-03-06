package robinson.mapactivity;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**˚
 * Created by gibl3t on 3/14/17.
 */

class GetTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String id;
    private User user;
    private Boolean isDone;

    private Gson GSON = new GsonBuilder().create();

    public GetTask(User user){
        this.user = user;
        id = user.getId();
    }

    protected void onPreExecute(){
    }

    protected String doInBackground(Void... urls){
        //do validation here

        try{
            URL url = new URL(MapsActivity.API_URL + "/users/" + id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("Connected to server");
            try{
                System.out.println("Entered try block");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response){
        if(response == null){
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        user = GSON.fromJson(response, User.class);
        System.out.println("server user is " + user.getLatitude());
    }

    public User getUser(){
        return user;
    }

}
