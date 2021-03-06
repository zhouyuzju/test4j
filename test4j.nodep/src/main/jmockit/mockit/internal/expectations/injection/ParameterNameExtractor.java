/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.expectations.injection;

import mockit.external.asm4.*;
import mockit.internal.*;
import mockit.internal.state.*;

final class ParameterNameExtractor extends ClassVisitor
{
   private final boolean forMethods;
   private String classDesc;
   private int methodAccess;
   private String methodName;
   private String methodDesc;

   ParameterNameExtractor(boolean forMethods) { this.forMethods = forMethods; }

   String extractNames(Class<?> classOfInterest)
   {
      String className = classOfInterest.getName();
      classDesc = className.replace('.', '/');

      if (!ParameterNames.hasNamesForClass(classDesc)) {
         ClassReader cr = ClassFile.createClassFileReader(classOfInterest);
         cr.accept(this, ClassReader.SKIP_FRAMES);
      }

      return classDesc;
   }

   @Override
   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
   {
      boolean visitingAMethod = name.charAt(0) != '<';

      if (visitingAMethod == forMethods) {
         methodAccess = access;
         methodName = name;
         methodDesc = desc;
         return new MethodOrConstructorVisitor();
      }

      return null;
   }

   private final class MethodOrConstructorVisitor extends MethodVisitor
   {
      private String previousDesc;
      private int previousIndex;

      @Override
      public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index)
      {
         int parameterIndex = index;

         if ("J".equals(previousDesc) || "D".equals(previousDesc)) {
            parameterIndex = previousIndex + 1;
         }

         ParameterNames.registerName(classDesc, methodAccess, methodName, methodDesc, name, parameterIndex);
         previousIndex = parameterIndex;
         previousDesc = desc;
      }
   }
}
