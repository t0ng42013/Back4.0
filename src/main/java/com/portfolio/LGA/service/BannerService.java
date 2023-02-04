package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.IBannerService;
import com.portfolio.LGA.dto.BannerDto;
import com.portfolio.LGA.model.Banner;
import com.portfolio.LGA.repository.BannerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class BannerService implements IBannerService {
    private final BannerRepository bannerRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public BannerService(BannerRepository bannerRepository, ModelMapper modelMapper) {
        this.bannerRepository = bannerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BannerDto> verBanner() {
        List<Banner> banner = bannerRepository.findAll();
        Type listType = new TypeToken<List<BannerDto>>(){}.getType();
        List<BannerDto> bannerDtos = modelMapper.map(banner, listType);
        return bannerDtos;
    }

    @Override
    public void crearBanner(BannerDto bannerDto) {
        Banner banner = modelMapper.map(bannerDto, Banner.class);
        bannerRepository.save(banner);
    }


    @Override
    public void borrarBanner(Long id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public Banner buscarBanner(Long id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public Banner editarBanner(BannerDto bannerDto) {
        Banner banners = bannerRepository.findById(bannerDto.getId()).orElse(null);
        modelMapper.map(bannerDto, banners);
        return bannerRepository.save(banners);
    }
}
