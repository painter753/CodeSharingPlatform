package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.CodeSnippet;

@Repository
public interface CodeRepository extends CrudRepository<CodeSnippet, String> { }
