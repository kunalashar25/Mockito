package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import com.mock.service.TodoServiceStub;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TodoImplStubTest {

    @Test
    public void testStubSize() {
        TodoService serviceStub = new TodoServiceStub();
        TodoImpl impl = new TodoImpl(serviceStub);

        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummy");

        Assert.assertEquals(2, filteredData.size());
    }

    @Test
    public void checkedFilteredData() {
        boolean flag = false;
        TodoService serviceStub = new TodoServiceStub();
        TodoImpl impl = new TodoImpl(serviceStub);

        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummy");
        for (String data : filteredData) {
            if (!data.contains("Spring"))
                flag = true;
        }

        if (flag)
            Assert.assertTrue("Filtered Data does not contain Spring word.", !flag);
    }
}
