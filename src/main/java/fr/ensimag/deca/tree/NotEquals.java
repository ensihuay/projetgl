package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;

/**
 *
 * @author gl41
 * @date 01/01/2016
 */
public class NotEquals extends AbstractOpExactCmp {

    public NotEquals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }

    @Override
    protected void codeGenCMP(DecacCompiler compiler){
        compiler.addInstruction(new BEQ(compiler.getLabel()));

    }

    @Override
    protected void codeGenNot(DecacCompiler compiler){
        compiler.addInstruction(new BNE(compiler.getLabel()));
    }

    @Override
    protected void codeGenBranch(DecacCompiler compiler, Label label) {
        compiler.addInstruction(new BNE(label));
    }
}
