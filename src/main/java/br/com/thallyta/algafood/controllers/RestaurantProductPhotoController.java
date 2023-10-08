package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.assembler.response.PhotoProductResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.ProductPhotoRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.ProductPhotoResponseDTO;
import br.com.thallyta.algafood.services.ProductService;
import br.com.thallyta.algafood.services.RestaurantPhotoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantPhotoProductService restaurantProductService;

    @Autowired
    private PhotoProductResponseDTOAssembler photoProductResponseDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoResponseDTO updateFile(@PathVariable  Long restaurantId,
                                              @PathVariable Long productId,
                                              @Valid ProductPhotoRequestDTO productPhotoRequestDTO) {

        Product product = productService.findOrFail(restaurantId, productId);
        MultipartFile file = productPhotoRequestDTO.getFile();
        
        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(productPhotoRequestDTO.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        ProductPhoto photoSaved = restaurantProductService.save(photo);
        return photoProductResponseDTOAssembler.toPhotoProductResponseDTO(photoSaved);

    }
}
