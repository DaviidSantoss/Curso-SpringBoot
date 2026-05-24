package Textos;

public class A20_PATCH {


    // =====
    // PATCH
    // =====


    // =====================================
    // porque usar?
    // =====================================

    /*
     *  1- Usado em cenários onde updates parciais são necessários.
     *
     *  2- Permite atualizar apenas campos específicos.
     *
     *  3- Reduz significativamente o tráfego e os riscos de exposição
     *  desncessária de dados.
     *
     *  4- Não é indepotente (A mesma requisição Path feita variás vezes pode
     * acabar resultando em valores diferente).
     *
     *  5- Existe o risco de colisões entre múltiplas requisições executadas
     * simultaneamente no mesmo recurso.
     *
     *  6- Há um risco cosideravel de uma requisição Path sobrescrever  a outra,
     * resultando em perda de dados ou currupção no estado do recurso.
     *
     *  7- Situações como essas são raras, mas podem acontecer e precisam ser levadas
     * em conta ao decidir quando e como usar o PATCH.
     *  */

    // =====================================
    // Quando usar
    // =====================================

    /*
    * 1- O PATCH é perfeito para atualizações rápidas e pontuais.
    *
    * 2- Em situações que demandam maior consistência e previsibilidade,
    * o PUT ainda é a opção mais recomendada.
    *
    * 3- Analise bem os requisitos do seu sistema e os possiveis impactos
    * antes de optar por ele.
    *  */

    // =====================================
    // Como Implementar
    // =====================================

    /*
    * 1- Criar uma Migration:
    *
    * Criamos uma Migration com o nome V5__Alter_Table_Person, contendo o seguinte
    * conteúdo, ""ALTER TABLE `person`ADD COLUMN `enabled` BIT(1) NOT NULL DEFAULT b'1' AFTER `gender`;"
    *
    * 2- Adicionamos esse campo "enable" a todas as classes necessárias, "Person,Person.dto e PersonDTO(de Tests)"
    *
    * 3- Implementamos um metodo dentro de PersonRepository que ira fazer a desabilitação de uma Person:
    *
    *       Avisa o Spring que é uma query de escrita
    *       @Modifying
            @Query("UPDATE Person p SET p.enabled = false where p.id =:id")
            void disablePerson(@Param("id") Long id);
    *
    * 4- Implementamos a lógica de negocio dentro de Service:
    *
    *   @Transactional
        public PersonDTO disablePerson(Long id) {


        logger.info("DESABILITAMOS ALGUEM !!");

        Valida se o id existe, lança exceção se não encontrar
        repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        Executa o UPDATE no banco
        repository.disablePerson(id);

        Busca a entidade atualizada e converte para DTO
        var entity= repository.findById(id).get();
        var dto =  parseObject(entity,PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }
    *
    * 5- Mapeamos o EndPoint PATCH
    *
    *
    *  @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
       @Override
       public PersonDTO disablePerson(@PathVariable("id") Long id){
       return service.disablePerson(id);
       }
    *
    *
    * 6- Documentação:
    *
    * → Adicionamos o endpoint no PersonControllerDocs
    *   para aparecer corretamente no Swagger/OpenAPI.
    *
    *
    * Extra:
    *
    * A maioria dos casos reais de PATCH envolve mudar o estado de algo.
    *
    *  Para listas Apenas as pessoas Habilitadas no find all mudamos a busca
    * diretamente no banco de dados:
    *
    *  @Query("SELECT p FROM Person p WHERE p.enabled = true")
       List<Person> findAllEnabled();
    * */

}
