package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.RINT;
import fr.ensimag.ima.pseudocode.instructions.WINT;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

import java.io.PrintStream;

/**
 *
 * @author gl41
 * @date 01/01/2016
 */
public class ReadInt extends AbstractReadExpr {

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        Type t = new IntType(compiler.getSymbols().create("int"));
        setType(t);

        return t;
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler){
        compiler.addInstruction(new WSTR("entrez un int"));
        compiler.addInstruction(new RINT());
        this.setdValue(Register.R1);

    }
    @Override
    protected void codeGenOPLeft(DecacCompiler compiler){
        this.codeGenInst(compiler);
    }
    @Override
    protected void codeGenOPRight(DecacCompiler compiler){
        this.codeGenInst(compiler);
    }


    @Override
    protected void codeGenPrint(DecacCompiler compiler) {
        this.codeGenInst(compiler);
        compiler.addInstruction(new WINT());
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.println("readInt();");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

}
