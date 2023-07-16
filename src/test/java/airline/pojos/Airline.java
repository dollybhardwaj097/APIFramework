package airline.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.apitestingframework.utils.DateUtils;
import org.apitestingframework.utils.RandomDataGenerator;
import org.apitestingframework.utils.RandomDataTypeNames;

import java.util.Arrays;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airline {
    //private String gender = Stream.of("male","female","other").findAny().get();
    //private String gender = Arrays.asList("male","female","other").get(RandomDataGenerator.getRandomNumber(0,3));
   // private GenderEnum gender;
    //private GenderEnum gender = Arrays.stream(GenderEnum.values()).findAny().get();
    private int id = Integer.parseInt(RandomDataGenerator.getRandomNumber(6));
    private String name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME);
    private String country = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY);
    private String logo = RandomDataGenerator.getRandomAlphabets(25);
    private String slogan = RandomDataGenerator.getRandomAlphabets(20);
    //private String head_quarters = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY);
    private String website = RandomDataGenerator.getRandomWebsiteName();
    private String established = String.valueOf(RandomDataGenerator.getRandomNumber(1900, DateUtils.getCurrentYear()));
}
