package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String tag, Map<String, String> atribTag) {
        super(tag, atribTag);
    }

    @Override
    public String toString() {
        /*String mapAsString = atribTag.keySet().stream()
                .map(key ->" " + key + "=" + "\"" + atribTag.get(key) + "\"")
                .collect(Collectors.joining());*/
        return "<" + tag + mapAsString() + ">";
    }
}
// END
