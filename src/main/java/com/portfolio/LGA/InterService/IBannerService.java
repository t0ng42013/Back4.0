package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.BannerDto;
import com.portfolio.LGA.model.Banner;

import java.util.List;

public interface IBannerService {
    public List<BannerDto> verBanner();

    public void crearBanner(BannerDto bannerDto);

    public void borrarBanner(Long id);

    public Banner buscarBanner(Long id);

    public Banner editarBanner(BannerDto bannerDto);
}
