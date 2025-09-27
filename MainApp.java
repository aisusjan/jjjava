// MainApp.java
// Demonstration of Adapter Pattern with Clean Code principles

interface BookReader {
    void open(String fileName);
    void read();
}

// Concrete implementation for EPUB
class EpubReader implements BookReader {
    private String fileName;

    @Override
    public void open(String fileName) {
        this.fileName = fileName;
        System.out.println("Opening EPUB book: " + fileName);
    }

    @Override
    public void read() {
        System.out.println("Reading EPUB book: " + fileName);
    }
}

// Adaptee class (incompatible interface)
class PdfReader {
    private String filePath;

    public void loadPdf(String filePath) {
        this.filePath = filePath;
        System.out.println("Loading PDF file: " + filePath);
    }

    public void displayPdf() {
        System.out.println("Displaying PDF content: " + filePath);
    }
}

// Adapter to make PdfReader compatible with BookReader
class PdfToEpubAdapter implements BookReader {
    private final PdfReader pdfReader;

    public PdfToEpubAdapter(PdfReader pdfReader) {
        this.pdfReader = pdfReader;
    }

    @Override
    public void open(String fileName) {
        pdfReader.loadPdf(fileName);
    }

    @Override
    public void read() {
        pdfReader.displayPdf();
    }
}

// Client
public class MainApp {
    public static void main(String[] args) {
        // Working with EPUB book
        BookReader epubBook = new EpubReader();
        epubBook.open("DesignPatterns.epub");
        epubBook.read();

        System.out.println("-----");

        // Using Adapter to read PDF as if it were EPUB
        PdfReader pdfReader = new PdfReader();
        BookReader pdfAdapter = new PdfToEpubAdapter(pdfReader);
        pdfAdapter.open("CleanCode.pdf");
        pdfAdapter.read();
    }
}
