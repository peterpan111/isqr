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
package by.kkc.web.isqr.repository.springdatajpa;

import by.kkc.web.isqr.model.Comment;
import by.kkc.web.isqr.repository.CommentRepository;
import org.springframework.data.repository.Repository;

/**
 * Spring Data JPA specialization of the {@link by.kkc.web.isqr.repository.CommentRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataCommentRepository extends CommentRepository, Repository<Comment, Integer> {
}
