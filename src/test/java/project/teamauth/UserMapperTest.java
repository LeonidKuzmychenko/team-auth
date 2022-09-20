package project.teamauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import project.teamauth.configuration.MapperConfiguration;
import project.teamauth.dtos.RegistrationRequestDto;
import project.teamauth.mappers.UserMapper;
import project.teamauth.models.Role;
import project.teamauth.models.User;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@RunWith(SpringRunner.class)
class UserMapperTest {

    private UserMapper userMapper;
    private ObjectWriter objectWriter;

    @BeforeEach
    public void init() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        this.userMapper = new UserMapper(modelMapper, new BCryptPasswordEncoder(), new Role(1L, "ROLE_USER"));
        this.objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
    }

    @Test
    void test1() throws JsonProcessingException {
//        User user = new User();
//        user.setEmail("Email");
//        user.setLogin("Login");
//        user.setPassword("$2a$10$ANGYM/DVgNHIfJ61QJ11ze5gyyTnHYHUWyA0JS1JkGYIJOPp2.lt6");

        RegistrationRequestDto requestDto = new RegistrationRequestDto();
        requestDto.setEmail("Email");
        requestDto.setLogin("Login");
        requestDto.setPassword1("Password");
        requestDto.setPassword2("Password");

        User user = userMapper.fromRegistrationDto(requestDto);

        System.out.println(objectWriter.writeValueAsString(user));

    }

}
