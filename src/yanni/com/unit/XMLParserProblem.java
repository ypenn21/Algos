package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class XMLParserProblem {
    // different String node types are START :: END :: DATA
    // tag value is the tag name;
    private static final String STA = "START";
    private static final String DATA = "DATA";
    private static final String END = "END";

    // this is a trie
    public class XmlTree {

        XmlElement root;
        Stack<XmlElement> startTags = new Stack<>();

        public void insert (String tag) {
            String[] tagMeta = tag.split("\\|");
            String type = tagMeta[0];
            String value = tagMeta[1];
            if (type.equals(STA) && value.equals("root")) {
                root = new XmlElement(value, type);
                startTags.add(root);
            } else if (type.equals(STA)) {
                XmlElement parentTag = startTags.peek();
                XmlElement add = new XmlElement(value, type);
                if (!parentTag.value.equals("root")) {
                    parentTag.childTags.add(add);
                } else {
                    root.childTags.add(add);
                }
                startTags.add(add);
            } else if (type.equals(DATA)) {
                XmlElement parentTag = startTags.peek();
                parentTag.childTags.add(new XmlElement(value, type));
            } else if (type.equals(END)) {
                startTags.pop();
                XmlElement parentTag = startTags.size()==0 ? null : startTags.peek();
                boolean success = ( parentTag != null ? parentTag.childTags.add(new XmlElement(value, type)) :
                                    root.childTags.add(new XmlElement(value, type)) );
            } else {
                throw new RuntimeException("NOT valid type");
            }
        }

        public String printToXml () {

            String xml = printToXml ( root, "" );
            System.out.println("------------");
            System.out.println(xml);
            return xml;
        }

        private String printToXml (XmlElement tag, String xml) {
            if(tag!=null) {
                System.out.println(tag.type + "|" + tag.value);
                xml += tag.type + "|" + tag.value+System.getProperty("line.separator");
            }
            for(XmlElement child : tag.childTags)
                xml = printToXml (child, xml);
            return xml;
        }

    }

    public class XmlElement {
        public XmlElement(String value, String type){
            this.value = value;
            this.type = type;
        }

        String value;
        String type;
        List<XmlElement> childTags = new ArrayList<>();
    }

    @Test
    public  void testXmlParser(){
        XmlTree treeXml = new XmlTree();
        treeXml.insert("START|root");
        treeXml.insert("START|video");
        treeXml.insert("START|name");
        treeXml.insert("DATA|FAST AND THE FURIOUS");
        treeXml.insert("END|name");
        treeXml.insert("END|video");
        treeXml.insert("END|root");
        String xml = treeXml.printToXml();
        assert(xml.equals("START|root\n" +
                "START|video\n" +
                "START|name\n" +
                "DATA|FAST AND THE FURIOUS\n" +
                "END|name\n" +
                "END|video\n" +
                "END|root\n"));
    }

}
