
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiListener extends Java8ParserBaseListener {

    @Override public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println(ctx.methodModifier().get(0).getText()+" "+
                         ctx.methodModifier().get(1).getText()+" "+
                         ctx.methodHeader().result().getText()+" "+
                         ctx.methodHeader().methodDeclarator().getText());
    }

    @Override public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        System.out.println(ctx.Identifier().getText());
    }
}
