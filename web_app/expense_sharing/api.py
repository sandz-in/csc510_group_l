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
            expense_dict = {"amount": expense.amount, "description": expense.description}
            result.append(expense_dict)
        return HttpResponse(json.dumps(result), content_type="application/json")
