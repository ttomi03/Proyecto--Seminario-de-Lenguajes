package com.example.proyectoseminario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectoseminario.model.ItemList;
import com.example.proyectoseminario.model.RetrofitClient;
import com.example.proyectoseminario.retrofit_data.RetrofitApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<ItemList> items;
    private final Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>(); // Inicializar la lista de elementos vacía

        // Crear y ejecutar un hilo para realizar la llamada a la API
        APIThread apiThread = new APIThread();
        apiThread.start();
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList item = items.get(position);

        // Picasso para cargar la imagen desde la URL
        Picasso.get().load(item.getImagen()).into(holder.imgItem);

        holder.tvTitulo.setText(item.getTitulo());
        holder.tvDescripcion.setText(item.getDescripcion());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("itemDetail", item);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private final ImageView imgItem;
        private final TextView tvTitulo;
        private final TextView tvDescripcion;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }

    // Implementacion de Hilos para el llamado de API
    class APIThread extends Thread {
        @Override
        public void run() {
            try {
                RetrofitApiService apiService = RetrofitClient.getApiService();

                Call<JsonObject> call = apiService.getDbItems();
                call.enqueue(new Callback<>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            JsonObject jsonObject = response.body();
                            if (jsonObject != null) {
                                JsonArray estrenoMovies = jsonObject.getAsJsonArray("estreno");
                                JsonArray terrorMovies = jsonObject.getAsJsonArray("terror");

                                items = new ArrayList<>();

                                // Procesar películas de la categoría "estreno"
                                for (JsonElement movieElement : estrenoMovies) {
                                    JsonObject movieObject = movieElement.getAsJsonObject();
                                    String title = movieObject.get("title").getAsString();
                                    String description = movieObject.get("description").getAsString();
                                    String image = movieObject.get("photo").getAsString();

                                    ItemList movie = new ItemList(title, description, image);
                                    items.add(movie);
                                }

                                // Procesar películas de la categoría "terror"
                                for (JsonElement movieElement : terrorMovies) {
                                    JsonObject movieObject = movieElement.getAsJsonObject();
                                    String title = movieObject.get("title").getAsString();
                                    String description = movieObject.get("description").getAsString();
                                    String image = movieObject.get("photo").getAsString();

                                    ItemList movie = new ItemList(title, description, image);
                                    items.add(movie);
                                }

                                notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                            }
                        } else {
                            // Manejar errores en la respuesta
                            Toast.makeText(context, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                            Log.e("API Response Error", "Response Code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                        // Manejar errores en la solicitud Retrofit
                        // Mostrar un toast en caso de error en la solicitud
                        Toast.makeText(context, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("API Request Error", "Error Message: " + t.getMessage());
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
