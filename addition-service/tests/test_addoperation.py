""" Test class for AddOperation """
import unittest
from project.addoperation import AddOperation


class AddOperationTestCases(unittest.TestCase):
    """ Test class for addoperation """

    def setUp(self):
        """ Setup function """
        print("In testcase: ", self._testMethodName)

    def test_process(self):
        """ Test function for testing process function """
        add_op = AddOperation(1, 2)
        add_op.process()
        self.assertEqual(add_op.result, 3)

    def test_get_output_json(self):
        """ Test function for testing output json """
        add_op = AddOperation(1, 2)
        output_json = add_op.get_output_json()
        result = output_json['result']['value']
        expected_output = 3

        self.assertEqual(expected_output, result)
