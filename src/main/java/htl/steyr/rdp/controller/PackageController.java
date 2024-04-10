package htl.steyr.rdp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/package")
public class PackageController {
    /**
     * @ToDo
     * 1) Create the SupplementaryPackageService - entity
     * 2) Create the repository
     * 3) Make changes in the SupplementaryPackageService - class
     * 4) Uncomment the code beneath
     */

    /*
    @Autowired
    private SupplementaryPackageService service;

    @GetMapping("/list")
    public ResponseEntity<List<SupplementaryPackageDto>> getFreeApartments() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
     */
}
