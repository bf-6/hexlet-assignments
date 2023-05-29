package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    public String bodyTag;
    public List<Tag> childrenTag;

    public PairedTag(String tag, Map<String, String> atribTag, String bodyTag, List<Tag> childrenTag) {
        super(tag, atribTag);
        this.bodyTag = bodyTag;
        this.childrenTag = childrenTag;
    }

    @Override
    public String toString() {
        String endTag = "</" + tag + ">";
        String res = childrenTag.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
        return "<" + tag + mapAsString() + ">" + bodyTag + res + endTag;
    }
}
// END
