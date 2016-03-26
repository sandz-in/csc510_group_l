import json

from django.http import HttpResponse
from rest_framework import views
from rest_framework import permissions

from expense_sharing.models import Expenses, DeleteAction

__author__ = 'sandz'


class ExpensesAPI(views.APIView):
    permission_classes = (
        permissions.IsAuthenticated,
    )

    def get(self, request):
        try:
            user = request.user
            print user.email
            result = []
            expenses = Expenses.objects.filter(user=user)

            for expense in expenses:
                expense_dict = {"id": expense.pk, "amount": expense.amount, "description": expense.description,
                                "currency": expense.currency, "added_on": str(expense.added_on),
                                "billtype": expense.billtype}
                result.append(expense_dict)
            return HttpResponse(json.dumps(result), content_type="application/json")
        except Exception as e:
            print e


class ExpenseShowAPI(views.APIView):
    permission_classes = (
        permissions.IsAuthenticated,
    )

    def get(self, request, expense_id):
        try:
            user = request.user
            expense = Expenses.objects.get(pk=expense_id)
            if user.pk == expense.user.pk:
                expense_dict = {"id": expense.pk, "amount": expense.amount, "description": expense.description,
                                "currency": expense.currency, "added_on": str(expense.added_on),
                                "billtype": expense.billtype}
                return HttpResponse(json.dumps(expense_dict), content_type="application/json")
        except Exception as e:
            print e


class ExpensesAddAPI(views.APIView):
    permission_classes = (
        permissions.IsAuthenticated,
    )

    def post(self, request):
        result = {"result": "success", "message": "Successfully added"}
        try:
            user = request.user
            expense = Expenses()
            expense.amount = request.POST.get("amount")
            expense.currency = request.POST.get("currency")
            expense.description = request.POST.get("description")
            expense.billtype = request.POST.get("billtype")
            expense.duration = request.POST.get("duration")
            expense.user = user
            expense.save()
        except Exception as e:
            result["result"] = "failure"
            result["message"] = "Failed to apply"
            print e
        return HttpResponse(json.dumps(result), content_type="application/json")


class DeleteActionAPI(views.APIView):
    permission_classes = (
        permissions.IsAuthenticated,
    )

    def post(self, request):
        result = {"result": "success", "message": "Successfully added"}
        try:
            user = request.user
            delete_action = DeleteAction()
            delete_action.user = user
            delete_action.billtype = request.POST.get("billtype")
            delete_action.save()
        except Exception as e:
            result["result"] = "failure"
            result["message"] = "Failed to apply"
            print e
        return HttpResponse(json.dumps(result), content_type="application/json")
