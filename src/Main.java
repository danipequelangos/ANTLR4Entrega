import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.antlr.v4.runtime.CharStreams.fromStream;

public class Main {
    public static void main(String[] args) throws IOException {
        String program = "";
        if(args.length > 0){
            program = args[0];
        }else{
            program = "Simple.java";
        }
        File initialFile = new File("src/"+program);
        InputStream targetStream = new FileInputStream(initialFile);
        CharStream input = fromStream(targetStream);

        Java8Lexer lexer = new Java8Lexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Java8Parser parser = new Java8Parser (tokens);

        ParseTree tree = parser.classDeclaration();

        ParseTreeWalker walker = new ParseTreeWalker();
        MiListener listener = new MiListener();
        walker.walk (listener, tree);
        listener.imprimirListaMetodos();
        System.out.println ();

    }
}
