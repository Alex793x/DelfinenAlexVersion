package application.data_handler;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.controllers.DataController;
import application.utility.SystemPrint;

public class AssociationHandler {

    public void addAssociation(DataController dataController) {
        SystemPrint.getInstance().promptEmployeeName();
        ListContainer.getInstance().getAssociationHashSet()
                .add(new CoachMemberAssociation<>(
                        dataController.getEmployeeHandler().findEmployee(),
                        dataController.getMembershipHandler().getLastLasMember(),
                        dataController.getPersonHandler().getLastPerson()));
    }
}
