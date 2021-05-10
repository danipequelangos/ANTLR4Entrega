
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiListener extends Java8ParserBaseListener {

    @Override public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println("---------------------------------------------------------");
        String metodo = "";
        for(int i = 0; i<ctx.methodModifier().size(); i++){
            metodo = metodo + ctx.methodModifier(i).getText()+" ";
        }
        int a = ctx.methodHeader().start.getStartIndex();
        int b = ctx.methodHeader().stop.getStopIndex();
        Interval interval = new Interval(a,b);
        metodo = metodo + ctx.methodHeader().start.getInputStream().getText(interval);
        System.out.println(metodo);
    }

    //metodos void
    @Override public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        System.out.println("   "+ctx.start.getInputStream().getText(interval));
    }

    @Override public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        System.out.println("   "+ctx.start.getInputStream().getText(interval));
    }

    //metodos no void
    @Override public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        System.out.println("   "+ctx.start.getInputStream().getText(interval));
    }
}
