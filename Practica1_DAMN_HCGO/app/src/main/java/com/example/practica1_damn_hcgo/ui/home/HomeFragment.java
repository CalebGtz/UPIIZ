package com.example.practica1_damn_hcgo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.practica1_damn_hcgo.databinding.FragmentHomeBinding;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configura el carrusel (si usas WhyNotImageCarousel)
        ImageCarousel carousel = binding.carousel; // <-- usa el id de tu XML
        // Quita esto si cargas drawables locales
        carousel.addData(new CarouselItem("https://smapse.com/storage/2018/10/pag-30-ar-rector-agencia.jpg", "Rectoría"));
        carousel.addData(new CarouselItem("https://www.ipn.mx/assets/files/posgrado/img/eventos/congreso/2025/1080x1080.png", "Eventos IPN"));
        carousel.addData(new CarouselItem("https://pbs.twimg.com/profile_images/1839073077205835777/xFe5qlkF_400x400.jpg", "Cultura"));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
