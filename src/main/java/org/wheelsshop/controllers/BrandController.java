package org.wheelsshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wheelsshop.entities.Brand;
import org.wheelsshop.services.BrandService;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController extends AbstractController<Brand> {
    @Autowired
    public BrandController(BrandService service) {
        super(service);
    }
}
