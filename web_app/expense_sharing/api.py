import json

from django.http import HttpResponse
from rest_framework import views
from rest_framework import permissions

from expense_sharing.models import Expenses

__author__ = 'sandz'


class ExpensesAPI(views.APIView):
    permission_classes = (
        permissions.IsAuthenticated,
    )

    def get(self, request):
        user = request.user
        print user.email
        result = []
        expenses = Expenses.objects.filter(user=user)

        for expense in expenses:
            expense_dict = {"id": expense.pk, "amount": expense.amount, "description": expense.description,
                            "currency": expense.currency, "added_on": expense.added_on}
            result.append(expense_dict)
        return HttpResponse(json.dumps(result), content_type="application/json")


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
                                "currency": expense.currency, "added_on": str(expense.added_on)}
                return HttpResponse(json.dumps(expense_dict), content_type="application/json")
        except Exception as e:
            print e
