package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wheelsshop.dto.BrandDto;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.request.BrandRequest;
import org.wheelsshop.services.BrandService;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController extends AbstractController<BrandDto, Brand, BrandRequest> {
    @Autowired
    public BrandController(BrandService service) {
        super(service);
    }
}
