package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 3/15/2018.
 */

public class ticket_OfHistoryLayout_Model {
    public ticket_OfHistoryLayout_Model(CoachListModel coachListModel, TicketListModel ticketListModel) {
        this.coachListModel = coachListModel;
        this.ticketListModel = ticketListModel;
    }

    CoachListModel coachListModel;
   TicketListModel ticketListModel;
}
