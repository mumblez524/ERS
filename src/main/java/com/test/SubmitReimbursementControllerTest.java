package com.test;

import com.business.Reimbursement;
import com.servlets.employee.SubmitReimbursementController;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubmitReimbursementControllerTest {

    SubmitReimbursementController stc = new SubmitReimbursementController();

    Reimbursement r = new Reimbursement(1,2, "Mitch", 20, "money", "PENDING", 0);

    @Test
    public void newReimbursement() {
        assertTrue(stc.newReimbursement(r));
    }
}