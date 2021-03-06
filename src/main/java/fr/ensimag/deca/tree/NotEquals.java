package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.SNE;

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
    protected void fetchCond(DecacCompiler compiler, GPRegister register) {
        compiler.addInstruction(new SNE(register));
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }

    @Override
    protected void codeGenCMPOP(DecacCompiler compiler){
        compiler.addInstruction(new BEQ(compiler.getLblManager().getLabelFalse()));

    }

    @Override
    protected void codeGenNot(DecacCompiler compiler){
        compiler.addInstruction(new BNE(compiler.getLblManager().getLabelFalse()));
    }


}
