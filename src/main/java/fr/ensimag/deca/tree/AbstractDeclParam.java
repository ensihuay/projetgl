package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;

/**
 * Individual variable declaration (e.g. "x = 42" in "int x = 42, z;")
 *
 * @author gl41
 * @date 01/01/2016
 */
public abstract class AbstractDeclParam extends Tree {

    protected abstract Type verifyMembers(DecacCompiler compiler,
                                          EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError;

    protected abstract void verifyBody(DecacCompiler compiler,
                                          EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError;

    /**
     * Generates the code for the declaration
     * @param compiler
     */
    protected abstract void codeGenDecl(DecacCompiler compiler);

    public abstract void setIndice(int i);
    public abstract int getIndice();
}
