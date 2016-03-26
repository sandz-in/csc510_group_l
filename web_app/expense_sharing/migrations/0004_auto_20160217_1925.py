# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0003_auto_20160217_1903'),
    ]

    operations = [
        migrations.AlterField(
            model_name='expenses',
            name='added_on',
            field=models.DateTimeField(auto_now_add=True),
        ),
    ]
