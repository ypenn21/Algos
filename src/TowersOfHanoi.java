import java.util.ArrayList;
import java.util.List;

public class TowersOfHanoi {

   public void solve(int n, String start, String auxiliary, String end) {
       if (n == 1) {
           System.out.println(start + " -> " + end);
       } else {
           solve(n - 1, start, end, auxiliary);
           System.out.println(start + " -> " + end);
           solve(n - 1, auxiliary, start, end);
       }
   }

   public static void main(String[] args) {
       TowersOfHanoi towersOfHanoi = new TowersOfHanoi();

       towersOfHanoi.solve(3, "1", "2", "3");

//       String hi = "asdfas";
//       if ( hi == null || hi.trim().equals("") ){
//           System.out.println("hey we did it 1");
//       }
//
//       String contentWithProblem ="{\"Airfare_Classzzz\":{\"label\":\"[[#{expense_app.uda.label.class}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Airfare_Class\"},\"BusinessPurpose\":{\"label\":\"[[#{expense_app.uda.label.asd}]]\",\"udaDataType\":\"StringValue\"},\"Car_Type\":{\"label\":\"\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Car_Type\"},\"Car_Vendor\":{\"label\":\"[[#{expense_app.uda.label.agency}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Car_Vendor\"},\"ClientInfo\":{\"label\":\"[[#{expense_app.uda.label.client_info}]]\",\"udaDataType\":\"StringValue\"},\"FR_Engine_Size\":{\"label\":\"[[#{expense_app.uda.label.engine_size}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"FR_Engine_Size\"},\"From_To\":{\"label\":\"[[#{expense_app.uda.label.from_to}]]\",\"udaDataType\":\"StringValue\"},\"FromTo\":{\"label\":\"[[#{expense_app.uda.label.from_to}]]\",\"udaDataType\":\"StringValue\"},\"HoldPayment\":{\"label\":\"[[#{expense_app.uda.label.hold_payment}]]\",\"udaDataType\":\"BooleanValue\"},\"InvCostCode\":{\"label\":\"[[#{expense_app.uda.label.cost_code}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"InvCostCode\"},\"InvSrvcCode\":{\"label\":\"[[#{expense_app.uda.label.service_type}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"InvSrvcCode\"},\"Passengers\":{\"label\":\"[[#{expense_app.uda.label.passenger}]]\",\"udaDataType\":\"NumberValue\"},\"PONumber\":{\"label\":\"[[#{expense_app.uda.label.po_number}]]\",\"udaDataType\":\"StringValue\"},\"PoProjectCodes\":{\"label\":\"[[#{expense_app.uda.label.project_code}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"POProjects\"},\"Rate2\":{\"label\":\"[[#{expense_app.uda.label.rate2}]]\",\"udaDataType\":\"NumberValue\"},\"SalesTax\":{\"label\":\"[[#{expense_app.uda.label.sales_tax}]]\",\"udaDataType\":\"BooleanValue\"},\"Time\":{\"label\":\"[[#{expense_app.uda.label.time}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Time\"},\"TipAmt\":{\"label\":\"[[#{expense_app.uda.label.tip_amt}]]\",\"udaDataType\":\"NumberValue\"},\"TipAmtPct\":{\"label\":\"[[#{expense_app.uda.label.}]]\",\"udaDataType\":\"NumberValue\"},\"TranDate\":{\"label\":\"[[#{expense_app.uda.label.service_date}]]\",\"udaDataType\":\"DateValue\"},\"TravelSvc\":{\"label\":\"[[#{expense_app.uda.label.approved_travel_agent_booking}]]\",\"udaDataType\":\"BooleanValue\"},\"uda_PerDiem_Lodging_EndDate_0\":{\"label\":\"[[#{expense_app.uda.label.end_date}]]\",\"udaDataType\":\"DateValue\"},\"uda_PerDiem_Lodging_StartDate_0\":{\"label\":\"[[#{expense_app.uda.label.start_date}]]\",\"udaDataType\":\"DateValue\"},\"VatBase1_GB\":{\"label\":\"[[#{expense_app.uda.label.vatbase1_gb}]]\",\"udaDataType\":\"CurrencyValue\"},\"VatBase2_GB\":{\"label\":\"[[#{expense_app.uda.label.vatbase2_gb}]]\",\"udaDataType\":\"CurrencyValue\"},\"VatType_EventTickets\":{\"label\":\"[[#{expense_app.uda.label.entertaining}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"VatType_EventTickets\"},\"VatType_Invoice1\":{\"label\":\"[[#{expense_app.uda.label.firm_invoice}]]\",\"udaDataType\":\"BooleanValue\"},\"VatType_Mileage1\":{\"label\":\"[[#{expense_app.uda.label.50_km}]]\",\"udaDataType\":\"BooleanValue\"},\"VatType_Travel1\":{\"label\":\"[[#{expense_app.uda.label.international}]]\",\"udaDataType\":\"BooleanValue\"},\"VatType_Venue1\":{\"label\":\"[[#{expense_app.uda.label.take_away}]]\",\"udaDataType\":\"BooleanValue\"},\"AssignedOfficeList\":{\"label\":\"[[#{expense_app.uda.label.assignedofficelist}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"OFF\"},\"DailyRate\":{\"label\":\"[[#{expense_app.uda.label.dailyrate}]]\",\"udaDataType\":\"CurrencyValue\"},\"ExcessLodging\":{\"label\":\"[[#{expense_app.uda.label.excesslodging}]]\",\"udaDataType\":\"CurrencyValue\"},\"Location\":{\"label\":\"[[#{expense_app.uda.label.location}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"OFF\"},\"NumDays\":{\"label\":\"[[#{expense_app.uda.label.numdays}]]\",\"udaDataType\":\"NumberValue\"},\"NumNights\":{\"label\":\"[[#{expense_app.uda.label.numnights}]]\",\"udaDataType\":\"NumberValue\"},\"PAPDLodgingRate\":{\"label\":\"[[#{expense_app.uda.label.papdlodgingrate}]]\",\"udaDataType\":\"CurrencyValue\"},\"PAPDMealRate\":{\"label\":\"[[#{expense_app.uda.label.papdmealrate}]]\",\"udaDataType\":\"CurrencyValue\"},\"PAPDMileageRate\":{\"label\":\"[[#{expense_app.uda.label.papdmileagerate}]]\",\"udaDataType\":\"CurrencyValue\"},\"PATravelType\":{\"label\":\"[[#{expense_app.uda.label.patraveltype}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"TravelType\"},\"Task_1\":{\"label\":\"[[#{expense_app.uda.label.task}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Task\"},\"Test1\":{\"label\":\"[[#{expense_app.uda.label.test_1}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Car_Type\"},\"Test2\":{\"label\":\"[[#{expense_app.uda.label.test2}]]\",\"udaDataType\":\"EntityValue\",\"entityTypeCode\":\"Car_Type\"},\"Venue\":{\"label\":\"[[#{expense_app.uda.label.venue}]]\",\"udaDataType\":\"StringValue\"},\"Venue123\":{\"label\":\"[[#{expense_app.uda.label.venue123}]]\",\"udaDataType\":\"StringValue\"}}";
//       //System.out.println(json.matches("^\\{(.|\\n|\\r)*}$"));
//       if( uda.matches("^<") //&& !uda.matches("^\\{(.|\\n|\\r)*}$")
//               ){
//           System.out.println("hey we did it");
//       }
       //System.out.print("Enter number of discs: ");
       //int discs = 2;
       //towersOfHanoi.solve(discs, "A", "B", "C");
       StringBuffer result = new StringBuffer("iojsdfgs|o9df^u7389rurnw$8er7|h9weu");  //Using buffer because the string length can grow to be a large string
       for (String token : result.toString().split("[|^$]")) {
           System.out.println(token);
       }
   }
}