from django.conf.urls import patterns, url

from expense_sharing.api import ExpensesAPI

__author__ = 'sandz'

urlpatterns = patterns('',
                       url(r"^api/expenses/$", ExpensesAPI.as_view()),
                       )
