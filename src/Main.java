import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.antlr.v4.runtime.CharStreams.fromStream;

public class Main {
    public static void main(String[] args) throws IOException {
        File initialFile = new File("src/Simple.java");
        InputStream targetStream = new FileInputStream(initialFile);
        CharStream input = fromStream(targetStream);

        // create a lexer that feeds off of input CharStream
        Java8Lexer lexer = new Java8Lexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        Java8Parser parser = new Java8Parser (tokens);

        ParseTree tree = parser.classDeclaration(); // begin parsing at init rule

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk (new MiListener(), tree);
        System.out.println ();

    }
}