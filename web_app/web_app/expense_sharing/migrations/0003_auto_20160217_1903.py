# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0002_expenses_currency'),
    ]

    operations = [
        migrations.AlterField(
            model_name='expenses',
            name='added_on',
            field=models.DateTimeField(),
        ),
    ]
