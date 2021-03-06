package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Label;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Print statement (print, println, ...).
 *
 * @author gl41
 * @date 01/01/2016
 */
public abstract class AbstractPrint extends AbstractInst {

    private boolean printHex;
    private ListExpr arguments = new ListExpr();
    
    abstract String getSuffix();

    public AbstractPrint(boolean printHex, ListExpr arguments) {
        Validate.notNull(arguments);
        this.arguments = arguments;
        this.printHex = printHex;
    }

    public ListExpr getArguments() {
        return arguments;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {

        if(arguments.size() == 0) {
            throw new ContextualError("Print takes at least one argument.", getLocation());
        }

        int argNumber = 1;
        for(AbstractExpr arg : arguments.getList()){
            Type argType = arg.verifyExpr(compiler, localEnv, currentClass);
            if(!(argType.isFloat() || argType.isString() || argType.isInt())) {
                String errorMsg = String.format("The argument number %d of function print(ln) must be a string, an int or a float. Got a ",argNumber);
                throw new ContextualError(errorMsg + argType.getName(), getLocation());
            }
            argNumber++;
        }

    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        for (AbstractExpr a : getArguments().getList()) {
            if(getPrintHex() && a.getType().isFloat())
                a.codeGenPrintX(compiler);
            else
                a.codeGenPrint(compiler);
        }
    }

    public boolean getPrintHex() {
        return printHex;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for(AbstractExpr a :this.arguments.getList()){
            s.print("print"+this.getSuffix()+"(");
            a.decompile(s);
            s.print(");");

        }
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        arguments.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        arguments.prettyPrint(s, prefix, true);
    }

}
