
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MiListener extends Java8ParserBaseListener {

    public static ArrayList<String> methodID = new ArrayList<>();
    public static ArrayList<String> imprimir = new ArrayList<>();

    @Override public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        String metodo = "";
        for(int i = 0; i<ctx.methodModifier().size(); i++){
            metodo = metodo + ctx.methodModifier(i).getText()+" ";
        }
        int a = ctx.methodHeader().start.getStartIndex();
        int b = ctx.methodHeader().stop.getStopIndex();
        Interval interval = new Interval(a,b);
        metodo = metodo + ctx.methodHeader().start.getInputStream().getText(interval);
        imprimir.add(metodo);
        methodID.add(ctx.methodHeader().methodDeclarator().Identifier().getText());
    }

    //metodos void
    @Override public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        imprimir.add(ctx.start.getInputStream().getText(interval));
    }

    @Override public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        imprimir.add(ctx.start.getInputStream().getText(interval));
    }

    //metodos no void
    @Override public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        imprimir.add(ctx.start.getInputStream().getText(interval));
    }

    public void imprimirListaMetodos(){
        for(int i = 0; i < imprimir.size(); i++){
            if(imprimir.get(i).matches("public.*")){
                System.out.println("---------------------------------------------------------");
                System.out.println(imprimir.get(i));
                continue;
            }
            for(int j = 0; j < methodID.size(); j++){
                if(imprimir.get(i).matches(methodID.get(j)+".*")){
                    System.out.println("   "+imprimir.get(i));
                }
            }
        }
    }
}
