from django.conf.urls import patterns, url

from expense_sharing.api import ExpensesAPI, ExpenseShowAPI

__author__ = 'sandz'

urlpatterns = patterns('',
                       url(r"^api/expenses/$", ExpensesAPI.as_view()),
                       url(r"^api/expense/(\d+)/$", ExpenseShowAPI.as_view()),
                       )
