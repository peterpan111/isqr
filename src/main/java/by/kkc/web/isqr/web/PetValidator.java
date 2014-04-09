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

import by.kkc.web.isqr.model.Response;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Response</code> forms.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class PetValidator {

    public void validate(Response response, Errors errors) {
        String name = response.getName();
        // name validaation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", "required", "required");
        } else if (response.isNew() && response.getService().getPet(name, true) != null) {
            errors.rejectValue("name", "duplicate", "already exists");
        }
        
        // type valication
        if (response.isNew() && response.getType() == null) {
            errors.rejectValue("type", "required", "required");
        }
        
     // type valication
        if (response.getBirthDate()==null) {
            errors.rejectValue("birthDate", "required", "required");
        }
    }

}
