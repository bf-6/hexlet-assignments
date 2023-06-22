package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class Tag {
    public String tag;
    public Map<String, String> atribTag;

    public Tag(String tag, Map<String, String> atribTag) {
        this.tag = tag;
        this.atribTag = atribTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getAtribTag() {
        return atribTag;
    }

    public void setAtribTag(Map<String, String> atribTag) {
        this.atribTag = atribTag;
    }
    public String mapAsString() {
        String newString = (String) atribTag.keySet().stream()
                .map(key -> " " + key + "=" + "\"" + atribTag.get(key) + "\"")
                .collect(Collectors.joining());
        return newString;
    }
}
// END
