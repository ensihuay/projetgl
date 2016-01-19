package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BGE;
import fr.ensimag.ima.pseudocode.instructions.BGT;
import fr.ensimag.ima.pseudocode.instructions.BLE;

/**
 *
 * @author gl41
 * @date 01/01/2016
 */
public class LowerOrEqual extends AbstractOpIneq {
    public LowerOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<=";
    }

    @Override
    protected void codeGenCMPOP(DecacCompiler compiler){
        compiler.addInstruction(new BGT(compiler.getLabelFalse()));
    }

    @Override
    protected void codeGenNot(DecacCompiler compiler){
        compiler.addInstruction(new BLE(compiler.getLabelFalse()));
    }


}
