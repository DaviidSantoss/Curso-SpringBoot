package Santos.David.mapper;

import Santos.David.data.dto.v1.PersonDTO;
import Santos.David.data.dto.v2.PersonDTOV2;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import tools.jackson.databind.ser.std.SimpleFilterProvider;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    /*Instância única do Dozer responsável por realizar o mapeamento
    * automático entre objetos (ex: Entity para DTO)*/
    private  static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    /*  Metodo genérico responsável por converter um objeto em outro objeto
    *   Exemplo: converter User (Entity) em UserDTO*/
    public static <O, D> D parseObject(O origin, Class<D> destination) {

        /* origin = objeto de origem (ex: User)
        * destination = classe de destino (ex: UserDTO.class)*/

        /*mapper.map faz a conversão automática copiando os campos com mesmo nome*/
        return mapper.map(origin, destination);
    }

    /*Metodo responsável por converter uma lista de objetos em outra lista
    * Exemplo: converter List<User> em List<UserDTO>  */
    public static  <O,D> List<D>  parseListObjects(List<O> origin, Class<D> destination) {

        /*Cria uma nova lista que irá armazenar os objetos convertidos  */
        List<D> destinationsObjects = new ArrayList<D>();

        /*Percorre cada objeto da lista de origem  */
        for (O o : origin) {

            /*Converte o objeto atual usando o ModelMapper
            * e adiciona na lista de destino.  */
            destinationsObjects.add(mapper.map(o, destination));
        }

        return destinationsObjects;
    }


}
