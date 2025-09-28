public abstract class FileSystemComponent {
    protected String name;
    public FileSystemComponent(String name) { this.name = name; }
    public abstract long getSize();
    public abstract void print(String indent);
}