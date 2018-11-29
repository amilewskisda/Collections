
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**      org.apache.commons.lang3.StringUtils.isBlank():

         StringUtils.isBlank(null)      = true
         StringUtils.isBlank("")        = true
         StringUtils.isBlank(" ")       = true
         StringUtils.isBlank("bob")     = false
         StringUtils.isBlank("  bob  ") = false

         StringUtils.isEmpty():

         StringUtils.isEmpty(null)      = true
         StringUtils.isEmpty("")        = true
         StringUtils.isEmpty(" ")       = false
         StringUtils.isEmpty("bob")     = false
         StringUtils.isEmpty("  bob  ") = false
 */

//Napisz metodę przetwarzającą podaną tablicę animals na set
// z zachowaniem kolejności elementów z oryginalnej tablicy
// + należy zapewnić poprawne wyelimowanie duplikatów (" dog" "dog")

public class TabToSet {

    public static void main(String[] args) {
        String[] animals = new String[]{"cat", "dog ", "mouse", "rat",
                "pig", "rabbit", "hamster", " ", "parrot", "cat", "",
                "dog", "cat", "  pig", "dog", null};
        Set<String> animalsSet = changeToSet(animals);
        Set<String> animalsSetStream = changeToSetWithStream(animals);
    }

    private static Set<String> changeToSetWithStream(String[] animals) {
        return Arrays.stream(animals)
                .filter(e -> StringUtils.isNotBlank(e))
                .map(e -> e.trim())
                .collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
    }

    private static Set<String> changeToSet(String[] animals) {
        Set<String> result = new LinkedHashSet<>();
        for (String animal : animals) {
            if (StringUtils.isBlank(animal)) {
                continue;
            }
            result.add(animal.trim());
        }
        return result;
    }
}
