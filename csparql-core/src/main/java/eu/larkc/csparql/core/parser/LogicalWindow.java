/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * It represent the logical window of the stream with its range and window overlap identified
 * by the step field.
 * 
 * @author Marco
 */
public class LogicalWindow extends Window {

   private final RegistrationInfo.TimeUnit rangeTimeUnit;
   private final int step; // If it's equal to freq is't tumbling.
   private final RegistrationInfo.TimeUnit stepTimeUnit;

   public LogicalWindow(final int windowRange,
         final RegistrationInfo.TimeUnit rangeTimeUnit, final int step,
         final RegistrationInfo.TimeUnit stepTimeUnit) {
      this.rangeTimeUnit = rangeTimeUnit;
      this.step = step;
      this.stepTimeUnit = stepTimeUnit;
      this.windowRange = windowRange;
   }

   /**
    * @return the rangeTimeUnit
    */
   public RegistrationInfo.TimeUnit getRangeTimeUnit() {
      return this.rangeTimeUnit;
   }

   /**
    * @return the step
    */
   public int getStep() {
      return this.step;
   }

   /**
    * @return the stepTimeUnit
    */
   public RegistrationInfo.TimeUnit getStepTimeUnit() {
      return this.stepTimeUnit;
   }

   /**
    * Check if the windows are overlapping, with step != freq.
    * 
    * @return true if the windows doesn't overlap
    */
   public boolean isTumbling() {
      return this.windowRange == this.step && this.rangeTimeUnit.equals(this.stepTimeUnit);
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer();
      sb.append("Logical window, range = ");
      sb.append(this.windowRange);
      sb.append(" rangeTimeUnit = ");
      sb.append(this.rangeTimeUnit.toString());
      if (this.isTumbling()) {
         sb.append(" Tumbling");
      } else {
         sb.append(" step = ");
         sb.append(this.step);
         sb.append(" stepTimeUnit = ");
         sb.append(this.stepTimeUnit);
      }
      sb.append("\n");
      return sb.toString();
   }

}
