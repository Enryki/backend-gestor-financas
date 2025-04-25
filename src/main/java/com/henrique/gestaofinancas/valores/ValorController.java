package com.henrique.gestaofinancas.valores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/valor")
@CrossOrigin(origins = "http://localhost:4200")
public class ValorController {

}
