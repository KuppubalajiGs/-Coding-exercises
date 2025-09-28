public class FileLeaf extends FileSystemComponent {
    private final long size;
    public FileLeaf(String name, long size) { super(name); this.size = size; }
    @Override public long getSize() { return size; }
    @Override public void print(String indent) { System.out.println(indent + name + " (" + size + " bytes)"); }
}