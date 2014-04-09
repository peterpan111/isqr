/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package by.kkc.web.isqr.web;

import java.util.Collection;
import java.util.Map;

import by.kkc.web.isqr.model.Response;
import by.kkc.web.isqr.model.Service;
import by.kkc.web.isqr.service.ISQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@SessionAttributes("pet")
public class PetController {

    private final ISQRService ISQRService;


    @Autowired
    public PetController(ISQRService ISQRService) {
        this.ISQRService = ISQRService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.ISQRService.findPetTypes();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/owners/{ownerId}/pets/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("ownerId") int ownerId, Map<String, Object> model) {
        Service service = this.ISQRService.findOwnerById(ownerId);
        Response response = new Response();
        service.addPet(response);
        model.put("response", response);
        return "pets/createOrUpdatePetForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/pets/new", method = RequestMethod.POST)
    public String processCreationForm(@ModelAttribute("pet") Response response, BindingResult result, SessionStatus status) {
        new PetValidator().validate(response, result);
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            this.ISQRService.savePet(response);
            status.setComplete();
            return "redirect:/owners/{ownerId}";
        }
    }

    @RequestMapping(value = "/owners/*/pets/{petId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        Response response = this.ISQRService.findPetById(petId);
        model.put("response", response);
        return "pets/createOrUpdatePetForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public String processUpdateForm(@ModelAttribute("pet") Response response, BindingResult result, SessionStatus status) {
        // we're not using @Valid annotation here because it is easier to define such validation rule in Java
        new PetValidator().validate(response, result);
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            this.ISQRService.savePet(response);
            status.setComplete();
            return "redirect:/owners/{ownerId}";
        }
    }

}
