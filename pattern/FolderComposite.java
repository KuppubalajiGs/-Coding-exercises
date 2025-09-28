import java.util.ArrayList;
import java.util.List;

public class FolderComposite extends FileSystemComponent {
    private final List<FileSystemComponent> children = new ArrayList<>();
    public FolderComposite(String name) { super(name); }
    public void add(FileSystemComponent c) { children.add(c); }
    @Override public long getSize() {
        long s = 0; for (FileSystemComponent c : children) s += c.getSize(); return s;
    }
    @Override public void print(String indent) {
        System.out.println(indent + name + "/");
        for (FileSystemComponent c : children) c.print(indent + "  ");
    }
}